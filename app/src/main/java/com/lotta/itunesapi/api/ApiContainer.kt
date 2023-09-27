package com.lotta.itunesapi.api

class ApiContainer {
    enum class STATE{
        DEVELOPMENT,
        UAT,
        PRODUCTION
    }

    companion object{
        private const val development = "https://itunes.apple.com/"
        private const val  uat = "https://itunes.apple.com/"
        private const val  production = "https://itunes.apple.com/"

        var state = STATE.DEVELOPMENT

        fun endpoint() : String {
            val stringBuilder = StringBuilder()
            when(state){
                STATE.DEVELOPMENT -> {
                    stringBuilder.append(development)
                }
                STATE.UAT -> {
                    stringBuilder.append(uat)
                }
                STATE.PRODUCTION -> {
                    stringBuilder.append(production)
                }
            }
            return stringBuilder.toString()
        }
    }
}