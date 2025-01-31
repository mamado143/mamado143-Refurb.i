package com.example.refurbi.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Custom color definitions
val Blue200 = Color(0xFF90CAF9)
val Blue500 = Color(0xFF2196F3)
val Blue700 = Color(0xFF1976D2)
val Teal200 = Color(0xFF03DAC5)
val Gray200 = Color(0xFFEEEEEE)
val Gray500 = Color(0xFF9E9E9E)
val Gray700 = Color(0xFF616161)

// Light color scheme
val LightColorScheme = lightColorScheme(
    primary = Blue500,
    onPrimary = Color.White,
    primaryContainer = Blue700,
    onPrimaryContainer = Color.White,
    secondary = Teal200,
    onSecondary = Color.Black,
    background = Gray200,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black
)

// Dark color scheme
val DarkColorScheme = darkColorScheme(
    primary = Blue200,
    onPrimary = Color.Black,
    primaryContainer = Blue700,
    onPrimaryContainer = Color.White,
    secondary = Teal200,
    onSecondary = Color.Black,
    background = Gray700,
    onBackground = Color.White,
    surface = Gray500,
    onSurface = Color.White
)
