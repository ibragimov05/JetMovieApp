package uz.android.jetmovieapp.common.models

data class CastResponse(
    val id: Long,
    val cast: List<Cast>,
    val crew: List<Crew>,
)

data class Cast(
    val adult: Boolean,
    val gender: Long,
    val id: Long,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?,
    val cast_id: Long,
    val character: String,
    val credit_id: String,
    val order: Long,
)

data class Crew(
    val adult: Boolean,
    val gender: Long,
    val id: Long,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?,
    val credit_id: String,
    val department: String,
    val job: String,
)
