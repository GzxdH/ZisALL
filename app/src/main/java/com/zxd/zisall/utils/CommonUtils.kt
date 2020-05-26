package com.zxd.zisall.utils

import android.content.Context

class CommonUtils {
    companion object {
        val mContext: Context? = null

        fun decode(unicodeStr: String?): String? {
            if (unicodeStr == null) {
                return null
            }
            val retBuf = StringBuffer()
            val maxLoop = unicodeStr.length
            var i = 0
            while (i < maxLoop) {
                if (unicodeStr[i] == '\\') {
                    if (i < maxLoop - 5 && (unicodeStr[i + 1] == 'u' || unicodeStr[i + 1] == 'U')) try {
                        retBuf.append(unicodeStr.substring(i + 2, i + 6).toInt(16).toChar())
                        i += 5
                    } catch (localNumberFormatException: NumberFormatException) {
                        retBuf.append(unicodeStr[i])
                    } else retBuf.append(unicodeStr[i])
                } else {
                    retBuf.append(unicodeStr[i])
                }
                i++
            }
            return retBuf.toString()
        }

        /**
         * 获取当前应用的Application Context
         *
         * @return ApplicationContext context
         */
        fun getContext(): Context? {
            if (mContext != null) {
                return mContext
            }
            throw NullPointerException("应该首先初始化")
        }

        fun dp2px(dpValue: Int): Float {
            return (mContext?.resources?.displayMetrics?.density ?: 0.0f) * dpValue + 0.5f
        }

    }

}