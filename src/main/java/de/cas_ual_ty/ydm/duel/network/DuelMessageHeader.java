package de.cas_ual_ty.ydm.duel.network;

import de.cas_ual_ty.ydm.duel.DuelManager;
import de.cas_ual_ty.ydm.duel.block.DuelTileEntity;
import de.cas_ual_ty.ydm.duel.dueldisk.DuelEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.DistExecutor;

public abstract class DuelMessageHeader
{
    // Use DuelMessageUtility encodeHeader / decodeHeader
    
    public final DuelMessageHeaderType type;
    
    public DuelMessageHeader(DuelMessageHeaderType type)
    {
        this.type = type;
    }
    
    public void writeToBuf(PacketBuffer buf)
    {
    }
    
    public void readFromBuf(PacketBuffer buf)
    {
    }
    
    public abstract IDuelManagerProvider getDuelManager(PlayerEntity player);
    
    public static class ContainerHeader extends DuelMessageHeader
    {
        public ContainerHeader(DuelMessageHeaderType type)
        {
            super(type);
        }
        
        @Override
        public IDuelManagerProvider getDuelManager(PlayerEntity player)
        {
            return (IDuelManagerProvider) player.containerMenu;
        }
    }
    
    public static class TileEntityHeader extends DuelMessageHeader
    {
        public BlockPos pos;
        
        public TileEntityHeader(DuelMessageHeaderType type, BlockPos pos)
        {
            super(type);
            this.pos = pos;
        }
        
        public TileEntityHeader(DuelMessageHeaderType type)
        {
            super(type);
        }
        
        @Override
        public void writeToBuf(PacketBuffer buf)
        {
            buf.writeBlockPos(pos);
        }
        
        @Override
        public void readFromBuf(PacketBuffer buf)
        {
            pos = buf.readBlockPos();
        }
        
        @Override
        public IDuelManagerProvider getDuelManager(PlayerEntity player)
        {
            DuelTileEntity te0 = (DuelTileEntity) player.level.getBlockEntity(pos);
            DuelManager dm = te0.duelManager;
            
            return DistExecutor.<IDuelManagerProvider>unsafeRunForDist(
                    () -> () -> new de.cas_ual_ty.ydm.duel.network.ClientDuelManagerProvider(dm),
                    () -> () -> () -> dm);
        }
    }
    
    public static class EntityHeader extends DuelMessageHeader
    {
        public int entityId;
        
        public EntityHeader(DuelMessageHeaderType type, int entityId)
        {
            super(type);
            this.entityId = entityId;
        }
        
        public EntityHeader(DuelMessageHeaderType type)
        {
            super(type);
        }
        
        @Override
        public void writeToBuf(PacketBuffer buf)
        {
            buf.writeInt(entityId);
        }
        
        @Override
        public void readFromBuf(PacketBuffer buf)
        {
            entityId = buf.readInt();
        }
        
        @Override
        public IDuelManagerProvider getDuelManager(PlayerEntity player)
        {
            DuelEntity e = (DuelEntity) player.level.getEntity(entityId);
            DuelManager dm = e.duelManager;
            
            return DistExecutor.<IDuelManagerProvider>unsafeRunForDist(
                    () -> () -> new de.cas_ual_ty.ydm.duel.network.ClientDuelManagerProvider(dm),
                    () -> () -> () -> dm);
        }
    }
}
