package org.refurbi.app.domain.model.version

sealed class Platform {
    object Android: Platform()
    object IOS: Platform()
    object Web: Platform()
    object Desktop: Platform()
}