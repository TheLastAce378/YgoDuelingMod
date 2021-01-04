package de.cas_ual_ty.ydm.cardinventory;

import com.google.gson.JsonObject;

import de.cas_ual_ty.ydm.card.CardHolder;
import de.cas_ual_ty.ydm.util.JsonKeys;

public class CardHolderStack
{
    public CardHolder cardHolder;
    public int count;
    
    public CardHolderStack(CardHolder cardHolder)
    {
        this.cardHolder = cardHolder;
        this.count = 1;
    }
    
    public CardHolderStack(JsonObject json)
    {
        this.readFromJson(json);
    }
    
    public CardHolderStack merge(CardHolderStack wrapper)
    {
        this.count += wrapper.count;
        return this;
    }
    
    public int getCount()
    {
        return this.count;
    }
    
    public CardHolder getCardHolder()
    {
        JsonObject json = new JsonObject();
        this.cardHolder.writeToJson(json);
        return new CardHolder(json);
    }
    
    public CardHolder getKey()
    {
        return this.cardHolder;
    }
    
    public void writeToJson(JsonObject json)
    {
        this.cardHolder.writeToJson(json);
        json.addProperty(JsonKeys.COUNT, this.count);
    }
    
    public void readFromJson(JsonObject json)
    {
        this.cardHolder = new CardHolder(json);
        this.count = json.get(JsonKeys.COUNT).getAsInt();
    }
    
    public static int compareCardHolders(CardHolder h1, CardHolder h2)
    {
        // Compare card name
        int comp = h1.getCard().getName().compareTo(h2.getCard().getName());
        
        if(comp != 0)
        {
            return comp;
        }
        
        // Compare image index
        comp = Byte.compare(h1.getImageIndex(), h2.getImageIndex());
        
        if(comp != 0)
        {
            return comp;
        }
        
        // Compare set id
        comp = h1.getCode().compareTo(h2.getCode());
        
        if(comp != 0)
        {
            return comp;
        }
        
        // Compare rarity
        return h1.getRarity().compareTo(h2.getRarity());
    }
}
