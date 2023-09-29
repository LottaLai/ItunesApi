package com.lotta.itunesapi.api

class ApiContainer {
    enum class STATE {
        DEVELOPMENT,
        UAT,
        PRODUCTION
    }

    companion object {
        var state = STATE.DEVELOPMENT

        private const val development = "https://itunes.apple.com/"
        private const val uat = "https://itunes.apple.com/"
        private const val production = "https://itunes.apple.com/"


        fun endpoint(): String = when (state) {
            STATE.DEVELOPMENT -> { development }
            STATE.UAT -> { uat }
            STATE.PRODUCTION -> { production }
        }
    }
}