@file:Suppress("DEPRECATION")

package app.aaps.wear.complications

import android.app.PendingIntent
import android.support.wearable.complications.ComplicationData
import android.support.wearable.complications.ComplicationText
import app.aaps.core.interfaces.logging.LTag
import app.aaps.wear.data.RawDisplayData
import dagger.android.AndroidInjection

/*
 * Created by dlvoy on 2019-11-12
 */
class SgvComplication : BaseComplicationProviderService() {

    // Not derived from DaggerService, do injection here
    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun buildComplicationData(dataType: Int, raw: RawDisplayData, complicationPendingIntent: PendingIntent): ComplicationData? {
        var complicationData: ComplicationData? = null
        val slopeArrow: String
        if (raw.singleBg[0].slopeArrow == "\u2198") {
          slopeArrow = "\u2199"
        } else {
          slopeArrow = raw.singleBg[0].slopeArrow
        }
        when (dataType) {
            ComplicationData.TYPE_SHORT_TEXT -> {
                val builder = ComplicationData.Builder(ComplicationData.TYPE_SHORT_TEXT)
                    .setShortText(ComplicationText.plainText(raw.singleBg[0].sgvString + slopeArrow + "\uFE0E"))
                    .setShortTitle(ComplicationText.plainText(displayFormat.shortTrend(raw, 0)))
                    .setTapAction(complicationPendingIntent)
                complicationData = builder.build()
            }

            else                             -> aapsLogger.warn(LTag.WEAR, "Unexpected complication type $dataType")
        }
        return complicationData
    }

    override fun getProviderCanonicalName(): String = SgvComplication::class.java.canonicalName!!
    override fun usesSinceField(): Boolean = true
}
