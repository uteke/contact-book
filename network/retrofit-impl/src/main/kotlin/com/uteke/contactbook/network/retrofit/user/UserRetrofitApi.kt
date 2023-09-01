package com.uteke.contactbook.network.retrofit.user

import com.uteke.contactbook.network.retrofit.user.model.CoordinatesJsonModel
import com.uteke.contactbook.network.retrofit.user.model.DateOfBirthJsonModel
import com.uteke.contactbook.network.retrofit.user.model.GetUsersJsonModel
import com.uteke.contactbook.network.retrofit.user.model.InfoJsonModel
import com.uteke.contactbook.network.retrofit.user.model.LocationJsonModel
import com.uteke.contactbook.network.retrofit.user.model.LoginJsonModel
import com.uteke.contactbook.network.retrofit.user.model.NameJsonModel
import com.uteke.contactbook.network.retrofit.user.model.PictureJsonModel
import com.uteke.contactbook.network.retrofit.user.model.StreetJsonModel
import com.uteke.contactbook.network.user.UserApi
import com.uteke.contactbook.network.retrofit.user.model.UserJsonModel
import com.uteke.contactbook.network.user.model.CoordinatesApiModel
import com.uteke.contactbook.network.user.model.DateOfBirthApiModel
import com.uteke.contactbook.network.user.model.GetUsersApiModel
import com.uteke.contactbook.network.user.model.InfoApiModel
import com.uteke.contactbook.network.user.model.LocationApiModel
import com.uteke.contactbook.network.user.model.LoginApiModel
import com.uteke.contactbook.network.user.model.NameApiModel
import com.uteke.contactbook.network.user.model.PictureApiModel
import com.uteke.contactbook.network.user.model.StreetApiModel
import com.uteke.contactbook.network.user.model.UserApiException
import com.uteke.contactbook.network.user.model.UserApiModel
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class UserRetrofitApi(private val retrofit: Retrofit) : UserApi {
    private val client: UserClient by lazy {
        retrofit.create(UserClient::class.java)
    }

    override suspend fun getUsers(seed: String, limit: Int, page: Int): Result<GetUsersApiModel> =
        try {
            client.getUsers(seed = seed, page = page, results = limit)
                .let { Result.success(it.toGetUsersApiModel()) }
        } catch (exception: HttpException) {
            Result.failure(UserApiException(cause = exception))
        }

    private fun GetUsersJsonModel.toGetUsersApiModel() =
        GetUsersApiModel(
            results = results.map { it.toUserApiModel() },
            info = info.toInfoApiModel(),
        )

    private fun UserJsonModel.toUserApiModel() =
        UserApiModel(
            gender = gender,
            name = name.toNameApiModel(),
            picture = picture.toPictureApiModel(),
            dateOfBirth = dateOfBirth.toDateOfBirthApiModel(),
            login = login.toLoginApiModel(),
            nationality = nationality,
            location = location.toLocationApiModel(),
            email = email,
            cell = cell,
            phone = phone,
        )

    private fun LoginJsonModel.toLoginApiModel() =
        LoginApiModel(
            uuid = uuid,
            username = username,
        )

    private fun DateOfBirthJsonModel.toDateOfBirthApiModel() =
        DateOfBirthApiModel(
            date = date,
            age = age,
        )

    private fun NameJsonModel.toNameApiModel() =
        NameApiModel(
            title = title,
            first = first,
            last = last,
        )

    private fun PictureJsonModel.toPictureApiModel() =
        PictureApiModel(
            large = large,
            medium = medium,
            thumbnail = thumbnail,
        )

    private fun InfoJsonModel.toInfoApiModel() =
        InfoApiModel(
            seed = seed,
            results = results,
            page = page,
            version = version,
        )

    private fun LocationJsonModel.toLocationApiModel() =
        LocationApiModel(
            street = street.toStreetApiModel(),
            city = city,
            state = state,
            country = country,
            postcode = postcode,
            coordinates = coordinates.toCoordinatesApiModel(),
        )

    private fun CoordinatesJsonModel.toCoordinatesApiModel() =
        CoordinatesApiModel(
            latitude = latitude,
            longitude = longitude,
        )

    private fun StreetJsonModel.toStreetApiModel() =
        StreetApiModel(
            number = number,
            name = name,
        )

    private interface UserClient {
        @GET(".")
        suspend fun getUsers(
            @Query("seed") seed: String,
            @Query("results") results: Int,
            @Query("page") page: Int,
        ): GetUsersJsonModel
    }
}