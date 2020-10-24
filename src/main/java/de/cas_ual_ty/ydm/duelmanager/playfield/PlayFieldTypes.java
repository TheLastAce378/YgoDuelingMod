package de.cas_ual_ty.ydm.duelmanager.playfield;

import com.google.common.collect.ImmutableList;

import de.cas_ual_ty.ydm.duelmanager.CardPosition;
import de.cas_ual_ty.ydm.duelmanager.action.ActionIcons;
import de.cas_ual_ty.ydm.duelmanager.action.ActionTypes;
import de.cas_ual_ty.ydm.duelmanager.action.MoveBottomAction;
import de.cas_ual_ty.ydm.duelmanager.action.MoveTopAction;
import de.cas_ual_ty.ydm.duelmanager.action.ShowCardAction;
import de.cas_ual_ty.ydm.duelmanager.action.ShowZoneAction;
import de.cas_ual_ty.ydm.duelmanager.action.ShuffleAction;

public class PlayFieldTypes
{
    public static final PlayFieldType DEFAULT = new PlayFieldType()
        .addEntry(ZoneTypes.HAND, ZoneOwner.PLAYER1, 13, 102, 194, 32)
        .addEntrySlim(ZoneTypes.DECK, ZoneOwner.PLAYER1, 98, 68)
        .setLastToPlayer1Deck()
        .addEntryFull(ZoneTypes.SPELL_TRAP, ZoneOwner.PLAYER1, 68, 68)
        .repeat(-2, 0, 4)
        .addEntrySlim(ZoneTypes.EXTRA_DECK, ZoneOwner.PLAYER1, -98, 68)
        .setLastToPlayer1ExtraDeck()
        .addEntrySlim(ZoneTypes.GRAVEYARD, ZoneOwner.PLAYER1, 98, 34)
        .addEntryFull(ZoneTypes.MONSTER, ZoneOwner.PLAYER1, 68, 34)
        .repeat(-2, 0, 4)
        .addEntrySlim(ZoneTypes.FIELD_SPELL, ZoneOwner.PLAYER1, -98, 34)
        .addEntrySlim(ZoneTypes.BANISHED, ZoneOwner.PLAYER1, 98, 0)
        .addEntrySlim(ZoneTypes.EXTRA, ZoneOwner.PLAYER1, -98, 102)
        .repeatPlayerZonesForOpponent()
        .addEntryFull(ZoneTypes.EXTRA_MONSTER_RIGHT, ZoneOwner.NONE, 34, 0)
        .addEntryFull(ZoneTypes.EXTRA_MONSTER_LEFT, ZoneOwner.NONE, -34, 0)
        .newInteraction().icon(ActionIcons.ADD_TO_HAND).interactorUnequals(ZoneTypes.HAND).interactorCardNotNull().interacteeEquals(ZoneTypes.HAND).interaction((player, zone, card, hand) -> new MoveBottomAction(ActionTypes.MOVE_TO_BOTTOM, zone, card, hand, CardPosition.FACE_DOWN, player)).playerAndInteractorSameOwner().interactorAndInteracteeSameOwner().addInteraction()
        .newInteraction().icon(ActionIcons.TO_GRAVEYARD).interactorUnequals(ZoneTypes.GRAVEYARD).interactorCardNotNull().interacteeEquals(ZoneTypes.GRAVEYARD).interaction((player, zone, card, graveyard) -> new MoveTopAction(ActionTypes.MOVE_ON_TOP, zone, card, graveyard, CardPosition.ATK, player)).playerAndInteractorSameOwner().cardAndInteracteeSameOwner().addInteraction()
        .newInteraction().icon(ActionIcons.BANISH_FA).interactorUnequals(ZoneTypes.BANISHED).interactorCardNotNull().interacteeEquals(ZoneTypes.BANISHED).interaction((player, zone, card, banished) -> new MoveTopAction(ActionTypes.MOVE_ON_TOP, zone, card, banished, CardPosition.ATK, player)).playerAndInteractorSameOwner().cardAndInteracteeSameOwner().addInteraction()
        .newInteraction().icon(ActionIcons.BANISH_FD).interactorUnequals(ZoneTypes.BANISHED).interactorCardNotNull().interacteeEquals(ZoneTypes.BANISHED).interaction((player, zone, card, banished) -> new MoveTopAction(ActionTypes.MOVE_ON_TOP, zone, card, banished, CardPosition.FACE_DOWN, player)).playerAndInteractorSameOwner().cardAndInteracteeSameOwner().addInteraction()
        .newInteraction().icon(ActionIcons.NORMAL_SUMMON).interactorEquals(ZoneTypes.HAND).interactorCardNotNull().interacteeEquals(ZoneTypes.MONSTER).interaction((player, zone, card, monster) -> new MoveTopAction(ActionTypes.MOVE_ON_TOP, zone, card, monster, CardPosition.ATK, player)).playerAndInteractorSameOwner().interactorAndInteracteeSameOwner().interacteeEmpty().addInteraction()
        .newInteraction().icon(ActionIcons.SET_MONSTER).interactorEquals(ZoneTypes.HAND).interactorCardNotNull().interacteeEquals(ZoneTypes.MONSTER).interaction((player, zone, card, monster) -> new MoveTopAction(ActionTypes.MOVE_ON_TOP, zone, card, monster, CardPosition.SET, player)).playerAndInteractorSameOwner().interactorAndInteracteeSameOwner().interacteeEmpty().addInteraction()
        .newInteraction().icon(ActionIcons.SPECIAL_SUMMON_ATK).interactorExcluded(PlayFieldTypes.getAllMonsterZones()).interactorCardNotNull().interacteeIncluded(PlayFieldTypes.getAllMonsterZones()).interaction((player, zone, card, monster) -> new MoveTopAction(ActionTypes.MOVE_ON_TOP, zone, card, monster, CardPosition.ATK, player)).playerAndInteractorSameOwner().interacteeEmpty().addInteraction()
        .newInteraction().icon(ActionIcons.SPECIAL_SUMMON_DEF).interactorExcluded(PlayFieldTypes.getAllMonsterZones()).interactorCardNotNull().interacteeIncluded(PlayFieldTypes.getAllMonsterZones()).interaction((player, zone, card, monster) -> new MoveTopAction(ActionTypes.MOVE_ON_TOP, zone, card, monster, CardPosition.DEF, player)).playerAndInteractorSameOwner().interacteeEmpty().addInteraction()
        .newInteraction().icon(ActionIcons.SPECIAL_SUMMON_SET).interactorIncluded(PlayFieldTypes.getAllStackZones()).interactorCardNotNull().interacteeEquals(ZoneTypes.MONSTER).interaction((player, zone, card, monster) -> new MoveTopAction(ActionTypes.MOVE_ON_TOP, zone, card, monster, CardPosition.SET, player)).playerAndInteractorSameOwner().interacteeEmpty().addInteraction()
        .newInteraction().icon(ActionIcons.ACTIVATE_SPELL_TRAP).interactorExcluded(PlayFieldTypes.getAllSpellZones()).interactorCardNotNull().interacteeIncluded(PlayFieldTypes.getAllSpellZones()).interaction((player, zone, card, monster) -> new MoveTopAction(ActionTypes.MOVE_ON_TOP, zone, card, monster, CardPosition.ATK, player)).playerAndInteractorSameOwner().interactorAndInteracteeSameOwner().interacteeEmpty().addInteraction()
        .newInteraction().icon(ActionIcons.SET_SPELL_TRAP_FD).interactorExcluded(PlayFieldTypes.getAllSpellZones()).interactorCardNotNull().interacteeIncluded(PlayFieldTypes.getAllSpellZones()).interaction((player, zone, card, monster) -> new MoveTopAction(ActionTypes.MOVE_ON_TOP, zone, card, monster, CardPosition.FACE_DOWN, player)).playerAndInteractorSameOwner().interactorAndInteracteeSameOwner().interacteeEmpty().addInteraction()
        .newInteraction().icon(ActionIcons.ACTIVATE_SPELL_TRAP).interactorIncluded(PlayFieldTypes.getAllSpellZones()).interactorCardNotNull().interacteeIncluded(PlayFieldTypes.getAllSpellZones()).interaction((player, zone, card, monster) -> new MoveTopAction(ActionTypes.MOVE_ON_TOP, zone, card, monster, CardPosition.ATK, player)).playerAndInteractorSameOwner().interactorEqualsInteractee().cardNotInPosition(CardPosition.ATK).addInteraction()
        .newInteraction().icon(ActionIcons.SET_SPELL_TRAP_FD).interactorIncluded(PlayFieldTypes.getAllSpellZones()).interactorCardNotNull().interacteeIncluded(PlayFieldTypes.getAllSpellZones()).interaction((player, zone, card, monster) -> new MoveTopAction(ActionTypes.MOVE_ON_TOP, zone, card, monster, CardPosition.FACE_DOWN, player)).playerAndInteractorSameOwner().interactorEqualsInteractee().cardNotInPosition(CardPosition.FACE_DOWN).addInteraction()
        .newInteraction().icon(ActionIcons.MOVE).interactorIncluded(PlayFieldTypes.getAllMonsterZones()).interactorCardNotNull().interacteeIncluded(PlayFieldTypes.getAllMonsterZones()).interaction((player, interactor, card, interactee) -> new MoveTopAction(ActionTypes.MOVE_ON_TOP, interactor, card, interactee, card.getCardPosition(), player)).playerAndInteractorSameOwner().interactorUnequalsInteractee().interacteeEmpty().addInteraction()
        //        .newInteraction().icon(ActionIcons.OVERLAY_TO_TOP).interactorIncluded(PlayFieldTypes.getAllMonsterZones()).interactorCardNotNull().interacteeIncluded(PlayFieldTypes.getAllMonsterZones()).interaction((player, interactor, card, interactee) -> new MoveTopAction(ActionTypes.MOVE_ON_TOP, interactor, card, interactee, card.getCardPosition())).playerAndInteractorSameOwner().interactorAndInteracteeSameOwner().interactorUnequalsInteractee().interacteeNonEmpty().addInteraction() //TODO all cards below must be put into ATK
        .newInteraction().icon(ActionIcons.OVERLAY_TO_BOTTOM).interactorIncluded(PlayFieldTypes.getAllMonsterZones()).interactorCardNotNull().interacteeIncluded(PlayFieldTypes.getAllMonsterZones()).interaction((player, interactor, card, interactee) -> new MoveBottomAction(ActionTypes.MOVE_TO_BOTTOM, interactor, card, interactee, CardPosition.ATK, player)).playerAndInteractorSameOwner().interactorAndInteracteeSameOwner().interactorUnequalsInteractee().interacteeNonEmpty().addInteraction()
        .newInteraction().icon(ActionIcons.SHUFFLE_DECK).interactorIncluded(PlayFieldTypes.getAllDeckZones()).interactorCardAny().interacteeIncluded(PlayFieldTypes.getAllDeckZones()).interaction((player, interactor, card, interactee) -> new ShuffleAction(ActionTypes.SHUFFLE, interactee)).playerAndInteractorSameOwner().interactorEqualsInteractee().addInteraction()
        .newInteraction().icon(ActionIcons.SHOW_HAND).interactorEquals(ZoneTypes.HAND).interactorCardAny().interacteeEquals(ZoneTypes.HAND).interaction((player, interactor, card, interactee) -> new ShowZoneAction(ActionTypes.SHOW_ZONE, interactor)).playerAndInteractorSameOwner().interactorUnequalsInteractee().addInteraction()
        .newInteraction().icon(ActionIcons.SHOW_CARD).interactorEquals(ZoneTypes.HAND).interactorCardNotNull().interacteeEquals(ZoneTypes.HAND).interaction((player, interactor, card, interactee) -> new ShowCardAction(ActionTypes.SHOW_CARD, interactor, card)).playerAndInteractorSameOwner().interactorUnequalsInteractee().addInteraction();
    
    public static ImmutableList<ZoneType> getAllMonsterZones()
    {
        return ImmutableList.of(ZoneTypes.MONSTER, ZoneTypes.EXTRA_MONSTER_RIGHT, ZoneTypes.EXTRA_MONSTER_LEFT);
    }
    
    public static ImmutableList<ZoneType> getAllStackZones()
    {
        return ImmutableList.of(ZoneTypes.DECK, ZoneTypes.EXTRA_DECK, ZoneTypes.GRAVEYARD, ZoneTypes.EXTRA);
    }
    
    public static ImmutableList<ZoneType> getAllDeckZones()
    {
        return ImmutableList.of(ZoneTypes.DECK, ZoneTypes.EXTRA_DECK);
    }
    
    public static ImmutableList<ZoneType> getAllSpellZones()
    {
        return ImmutableList.of(ZoneTypes.SPELL_TRAP, ZoneTypes.FIELD_SPELL);
    }
}
