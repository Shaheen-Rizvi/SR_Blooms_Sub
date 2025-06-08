package com.example.sr_blooms.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class CartViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    val deliveryCost = 100.00
    val taxCost = 150.00    // fixed typo: TasxCost -> taxCost
    val discount = 40.00    // changed Discount to discount (camelCase)

    val subTotal: Double
        get() = _cartItems.sumOf { it.price * it.quantity } + deliveryCost + taxCost - discount

    val cartSize: Int
        get() = _cartItems.sumOf { it.quantity }  // Sum of item quantities

    // Add item to cart
    fun addItemToCart(cartItem: CartItem) {
        _cartItems.add(cartItem)
    }

    // Remove an item from cart
    fun removeItem(cartItem: CartItem) {
        _cartItems.remove(cartItem)
    }

    // Clear entire cart
    fun clearCart() {
        _cartItems.clear()
    }
}
