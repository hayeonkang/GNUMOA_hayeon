package com.example.gnumoa_hayeon

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class getAlram : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // 푸시 알림 수신 시 처리할 내용을 여기에 작성합니다.
        // 알림 내용을 읽고, 사용자에게 알림을 표시하는 등의 작업을 수행할 수 있습니다.
        // remoteMessage 객체에서 알림 데이터를 가져와서 필요한 처리를 수행합니다.
        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body

        // 푸시 알림을 로그로 출력합니다.
        Log.d(TAG, "Received FCM Message - Title: $title, Body: $body")

        // 여기에서 추가적인 처리 작업을 수행할 수 있습니다.
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        // FCM 토큰이 갱신될 때마다 호출됩니다.
        // 새로운 토큰을 서버로 전송하거나 필요한 작업을 수행할 수 있습니다.
        Log.d(TAG, "Refreshed Token: $token")

        // 여기에서 추가적인 처리 작업을 수행할 수 있습니다.
    }

    companion object {
        private const val TAG = "MyFirebaseMessagingService"
    }
}



