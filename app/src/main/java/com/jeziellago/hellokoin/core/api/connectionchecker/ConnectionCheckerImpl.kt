package com.jeziellago.hellokoin.core.api.connectionchecker

import android.content.Context
import com.jeziellago.hellokoin.core.commonextensions.isConnected

/**
 * Created by jesselima on 31/01/21.
 * This is a part of the project Asteroid Radar.
 */
class ConnectionCheckerImpl(private val context: Context?) : ConnectionChecker {
	override fun isConnected(): Boolean {
		return context?.isConnected() ?: false
	}
}