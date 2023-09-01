package com.uteke.contactbook.network.retrofit.user

import com.squareup.moshi.Moshi
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
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class UserRetrofitApiTest {
    private val mockWebServer = MockWebServer()
    private lateinit var api: UserRetrofitApi

    @BeforeEach
    fun setUp() {
        mockWebServer.start()
        api = UserRetrofitApi(retrofit = mockRetrofit())
    }

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @ParameterizedTest
    @ValueSource(ints = [300, 400, 500])
    fun `getUsers GIVEN server returns 400 with body THEN returns Success of list of user`(httpCode: Int) =
        runTest {
            mockWebServer.enqueue(mockResponse(statusCode = httpCode))

            val result = api.getUsers(seed = "random", limit = 10, page = 1)

            assertTrue(result.isFailure && result.exceptionOrNull() is UserApiException)
        }

    @ParameterizedTest
    @ValueSource(ints = [200, 201, 202, 203, 206, 207, 208, 210, 226])
    fun `getUsers GIVEN server returns 200 with body THEN returns Success of list of user`(httpCode: Int) =
        runTest {
            mockWebServer.enqueue(
                response = mockResponse(statusCode = 200) {
                    //language=json
                    """
                        {
                            "results": [
                                {
                                    "gender": "female",
                                    "name": {
                                        "title": "Mrs",
                                        "first": "Frances",
                                        "last": "Herrera"
                                    },
                                    "location": {
                                        "street": {
                                            "number": 5740,
                                            "name": "W Dallas St"
                                        },
                                        "city": "Wollongong",
                                        "state": "Australian Capital Territory",
                                        "country": "Australia",
                                        "postcode": 3018,
                                        "coordinates": {
                                            "latitude": "-83.6031",
                                            "longitude": "18.6994"
                                        },
                                        "timezone": {
                                            "offset": "+2:00",
                                            "description": "Kaliningrad, South Africa"
                                        }
                                    },
                                    "email": "frances.herrera@example.com",
                                    "login": {
                                        "uuid": "56989ef1-ee6a-4d6b-a20f-18b343213437",
                                        "username": "yellowbutterfly577",
                                        "password": "8989",
                                        "salt": "3zM1zTzh", 
                                        "md5": "b0a240ad4aea0d1a4ef28339ed246c3f",
                                        "sha1": "32c3d0001a4379945544c56a8ef92f98dd6891b4",
                                        "sha256": "a5ed6f0b69b91ba8c444caf4b881f853fd5d36e6dfa68eddb11bebe21304b455"
                                    },
                                    "dob": {
                                        "date": "1983-05-27T11:26:06.318Z",
                                        "age": 40
                                    },
                                    "registered": {
                                        "date": "2006-01-06T13:34:20.469Z",
                                        "age": 17
                                    },
                                    "phone": "02-1122-1470",
                                    "cell": "0444-339-924",
                                    "id": {
                                        "name": "TFN",
                                        "value": "174195871"
                                    },
                                    "picture": {
                                        "large": "https://randomuser.me/api/portraits/women/27.jpg",
                                        "medium": "https://randomuser.me/api/portraits/med/women/27.jpg",
                                        "thumbnail": "https://randomuser.me/api/portraits/thumb/women/27.jpg"
                                    },
                                    "nat": "AU"
                                },
                                {
                                    "gender": "male",
                                    "name": {
                                        "title": "Mr",
                                        "first": "Juliano",
                                        "last": "da Mota"
                                    },
                                    "location": {
                                        "street": {
                                            "number": 71,
                                            "name": "Rua Treze "
                                        },
                                        "city": "Passos",
                                        "state": "Acre",
                                        "country": "Brazil",
                                        "postcode": 48678,
                                        "coordinates": {
                                            "latitude": "43.6207",
                                            "longitude": "-159.4352"
                                        },
                                        "timezone": {
                                            "offset": "-11:00",
                                            "description": "Midway Island, Samoa"
                                        }
                                    },
                                    "email": "juliano.damota@example.com",
                                    "login": {
                                        "uuid": "7745ef46-2c62-4e9e-b241-29400da064bc",
                                        "username": "lazypanda146",
                                        "password": "rocket",
                                        "salt": "pTWQ3mzu",
                                        "md5": "5830c974e8bc8537d6d527f3c270ed10",
                                        "sha1": "f55d9a750b0bcb0ce9d22ed60120eaee811ffb35",
                                        "sha256": "8d9a7c0e1eb67b38744bd111b64ab47a6b2029027fd514ff8b74153d5d2598fa"
                                    },
                                    "dob": {
                                        "date": "1992-03-28T11:45:34.772Z",
                                        "age": 31
                                    },
                                    "registered": {
                                        "date": "2019-02-17T07:29:54.396Z",
                                        "age": 4
                                    },
                                    "phone": "(63) 5577-5851",
                                    "cell": "(71) 1434-8746",
                                    "id": {
                                        "name": "",
                                        "value": null
                                    },
                                    "picture": {
                                        "large": "https://randomuser.me/api/portraits/men/88.jpg",
                                        "medium": "https://randomuser.me/api/portraits/med/men/88.jpg",
                                        "thumbnail": "https://randomuser.me/api/portraits/thumb/men/88.jpg"
                                    },
                                    "nat": "BR"
                                }
                            ],
                            "info": {
                                "seed": "random",
                                "results": 2,
                                "page": 1,
                                "version": "1.3"
                            }
                        }
                    """.trimIndent()
                },
            )

            val expected = Result.success(
                GetUsersApiModel(
                    results = listOf(
                        UserApiModel(
                            gender = "female",
                            name = NameApiModel(
                                title =  "Mrs",
                                first = "Frances",
                                last = "Herrera",
                            ),
                            picture = PictureApiModel(
                                large = "https://randomuser.me/api/portraits/women/27.jpg",
                                medium = "https://randomuser.me/api/portraits/med/women/27.jpg",
                                thumbnail = "https://randomuser.me/api/portraits/thumb/women/27.jpg",
                            ),
                            dateOfBirth = DateOfBirthApiModel(
                                date = "1983-05-27T11:26:06.318Z",
                                age = 40,
                            ),
                            login = LoginApiModel(
                                uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                                username = "yellowbutterfly577",
                            ),
                            nationality = "AU",
                            location = LocationApiModel(
                                street = StreetApiModel(
                                    number = 5740,
                                    name = "W Dallas St",
                                ),
                                city = "Wollongong",
                                state =  "Australian Capital Territory",
                                country = "Australia",
                                postcode = "3018",
                                coordinates = CoordinatesApiModel(
                                    latitude = "-83.6031",
                                    longitude = "18.6994",
                                )
                            ),
                            email = "frances.herrera@example.com",
                            phone = "02-1122-1470",
                            cell = "0444-339-924",
                        ),
                        UserApiModel(
                            gender = "male",
                            name = NameApiModel(
                                title =  "Mr",
                                first = "Juliano",
                                last = "da Mota",
                            ),
                            picture = PictureApiModel(
                                large = "https://randomuser.me/api/portraits/men/88.jpg",
                                medium = "https://randomuser.me/api/portraits/med/men/88.jpg",
                                thumbnail = "https://randomuser.me/api/portraits/thumb/men/88.jpg",
                            ),
                            dateOfBirth = DateOfBirthApiModel(
                                date = "1992-03-28T11:45:34.772Z",
                                age = 31,
                            ),
                            login = LoginApiModel(
                                uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                                username = "lazypanda146",
                            ),
                            nationality = "BR",
                            location = LocationApiModel(
                                street = StreetApiModel(
                                    number = 71,
                                    name = "Rua Treze ",
                                ),
                                city = "Passos",
                                state =  "Acre",
                                country = "Brazil",
                                postcode = "48678",
                                coordinates = CoordinatesApiModel(
                                    latitude = "43.6207",
                                    longitude = "-159.4352",
                                )
                            ),
                            email = "juliano.damota@example.com",
                            phone = "(63) 5577-5851",
                            cell = "(71) 1434-8746",
                        ),
                    ),
                    info = InfoApiModel(
                        seed = "random",
                        results = 2,
                        page = 1,
                        version = "1.3",
                    ),
                ),
            )

            assertEquals(expected, api.getUsers(seed = "random", limit = 10, page = 1))
        }

    private fun mockResponse(statusCode: Int, body: () -> String? = { null }) =
        MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .addHeader("Cache-Control", "no-cache")
            .setResponseCode(statusCode)
            .apply {
                body()?.let(::setBody)
            }

    private fun mockRetrofit() =
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .build()
}