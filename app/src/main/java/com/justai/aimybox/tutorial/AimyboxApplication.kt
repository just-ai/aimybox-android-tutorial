package com.justai.aimybox.tutorial

import android.app.Application
import android.content.Context
import com.justai.aimybox.Aimybox
import com.justai.aimybox.api.aimybox.AimyboxDialogApi
import com.justai.aimybox.components.AimyboxProvider
import com.justai.aimybox.core.Config
import com.justai.aimybox.speechkit.google.platform.GooglePlatformSpeechToText
import com.justai.aimybox.speechkit.google.platform.GooglePlatformTextToSpeech
import java.util.*

class AimyboxApplication : Application(), AimyboxProvider {

    companion object {
        private const val DIALOG_API_URL = "https://bot.aimylogic.com/chatapi/webhook/zenbox/STUyFXnD:d29d512055111e23a9915675215d0ed8f91080df"
    }

    override val aimybox by lazy { createAimybox(this) }

    private fun createAimybox(context: Context): Aimybox {
        val unitId = UUID.randomUUID().toString()

        val textToSpeech = GooglePlatformTextToSpeech(context, Locale.US)
        val speechToText = GooglePlatformSpeechToText(context, Locale.US)

        val dialogApi = AimyboxDialogApi("", unitId, DIALOG_API_URL)

        return Aimybox(Config.create(speechToText, textToSpeech, dialogApi))
    }
}