package com.devwindsw.kotlinunittest

class User(
    val id: String,
    val email: String,
    var fullName: String,
    var verificationStatus: VerificationStatus,
    var memberShipStatus: MemberShipStatus
) {
    enum class VerificationStatus {
        Verified
    }
    enum class MemberShipStatus {
        Free
    }
}
