package marc6697com.marc6697.footbalmatchschedulefix.API

import java.net.URL


class ApiRepository {
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}