package com.uteke.contactbook.features.userlist.data

import com.uteke.contactbook.network.user.model.NameApiModel
import com.uteke.contactbook.network.user.model.PictureApiModel
import com.uteke.contactbook.network.user.UserApi
import com.uteke.contactbook.network.user.model.DateOfBirthApiModel
import com.uteke.contactbook.network.user.model.GetUsersApiModel
import com.uteke.contactbook.network.user.model.InfoApiModel
import com.uteke.contactbook.network.user.model.LocationApiModel
import com.uteke.contactbook.network.user.model.LoginApiModel
import com.uteke.contactbook.network.user.model.StreetApiModel
import com.uteke.contactbook.network.user.model.UserApiModel

internal val fakeUserApiReturnsSuccessOfListOfUser = object : UserApi {
    override suspend fun getUsers(seed: String, limit: Int, page: Int): Result<GetUsersApiModel> =
        Result.success(
            GetUsersApiModel(
                results = listOf(
                    UserApiModel(
                        gender = "male",
                        name = NameApiModel(
                            title = "Mr",
                            first = "John",
                            last = "Doe",
                        ),
                        picture = PictureApiModel(
                            large  ="https://randomuser.me/api/portraits/men/2.jpg",
                            medium = "https://randomuser.me/api/portraits/med/men/2.jpg",
                            thumbnail = "https://randomuser.me/api/portraits/thumb/men/2.jpg",
                        ),
                        nationality = "US",
                        dateOfBirth = DateOfBirthApiModel(
                            date = "1983-05-27T11:26:06.318Z",
                            age = 27,
                        ),
                        login = LoginApiModel(
                            uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                            username = "yellowbutterfly577",
                        ),
                        location = LocationApiModel(
                            street = StreetApiModel(
                                number = 5740,
                                name = "W Dallas St",
                            ),
                            city = "Wollongong",
                            state =  "Australian Capital Territory",
                            country = "Australia",
                            postcode = "3018",
                        ),
                    ),
                    UserApiModel(
                        gender = "female",
                        name = NameApiModel(
                            title = "Mrs",
                            first = "Jane",
                            last = "Doe",
                        ),
                        picture = PictureApiModel(
                            large  ="https://randomuser.me/api/portraits/women/15.jpg",
                            medium = "https://randomuser.me/api/portraits/med/women/15.jpg",
                            thumbnail = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                        ),
                        nationality = "FR",
                        dateOfBirth = DateOfBirthApiModel(
                            date = "1983-05-27T11:26:06.318Z",
                            age = 35,
                        ),
                        login = LoginApiModel(
                            uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                            username = "lazypanda146",
                        ),
                        location = LocationApiModel(
                            street = StreetApiModel(
                                number = 71,
                                name = "Rua Treze ",
                            ),
                            city = "Passos",
                            state =  "Acre",
                            country = "Brazil",
                            postcode = "48678",
                        ),
                    ),
                ),
                info = InfoApiModel(
                    seed = "random",
                    results = 2,
                    page = 1,
                    version = "1.0",
                ),
            ),
        )
}

internal val fakeUserApiReturnsSuccessOfEmptyList = object : UserApi {
    override suspend fun getUsers(seed: String, limit: Int, page: Int): Result<GetUsersApiModel> =
        Result.success(
            GetUsersApiModel(
                results = emptyList(),
                info = InfoApiModel(
                    seed = "random",
                    results = 2,
                    page = 1,
                    version = "1.0",
                ),
            ),
        )
}

internal val fakeUserApiReturnsFailureOfThrowable = object : UserApi {
    override suspend fun getUsers(seed: String, limit: Int, page: Int): Result<GetUsersApiModel> =
        Result.failure(Throwable(message = "error message"))
}