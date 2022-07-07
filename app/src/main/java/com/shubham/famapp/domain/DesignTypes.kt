package com.shubham.famapp.domain

import com.shubham.famapp.domain.model.CardGroupModel

/**
 * Different types of design types of card list
 */
sealed class DesignTypes{
    abstract val id: Int
    data class SMALL_DISPLAY_CLASS(val cardData : CardGroupModel) :DesignTypes(){
        override val id: Int
            get() = cardData.id
    }
    data class BIG_DISPLAY_CARD(val cardData : CardGroupModel) :DesignTypes(){
        override val id: Int
            get() = cardData.id
    }
    data class IMAGE_CARD(val cardData : CardGroupModel) :DesignTypes(){
        override val id: Int
            get() = cardData.id
    }
    data class SMALL_CARD_WITH_ARROW(val cardData : CardGroupModel) :DesignTypes(){
        override val id: Int
            get() = cardData.id
    }
    data class DYNAMIC_WIDTH_CARD(val cardData : CardGroupModel) :DesignTypes(){
        override val id: Int
            get() = cardData.id
    }
    companion object{
        fun getType(designType: String,data: CardGroupModel):DesignTypes{
            return when(designType){
                "HC1" ->SMALL_DISPLAY_CLASS(data)
                "HC3" -> BIG_DISPLAY_CARD(data)
                "HC5" -> IMAGE_CARD(data)
                "HC6" -> SMALL_CARD_WITH_ARROW(data)
                else -> DYNAMIC_WIDTH_CARD(data)
            }
        }
    }
}
