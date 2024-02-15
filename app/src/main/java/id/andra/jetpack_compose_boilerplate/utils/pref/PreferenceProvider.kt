package id.andra.jetpack_compose_boilerplate.utils.pref

class PreferenceProvider(
    private val cacheManager: CacheManager
) {
    companion object {
        const val USER_ID = "pref_user_id"
    }
}