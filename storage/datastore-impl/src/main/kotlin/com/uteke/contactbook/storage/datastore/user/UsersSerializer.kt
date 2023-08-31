package com.uteke.contactbook.storage.datastore.user

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.uteke.contactbook.storage.datastore.user.UsersProto
import java.io.InputStream
import java.io.OutputStream

object UsersSerializer : Serializer<UsersProto> {
    override suspend fun readFrom(input: InputStream): UsersProto =
        try {
            UsersProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Error deserializing proto", exception)
        }

    override suspend fun writeTo(t: UsersProto, output: OutputStream) =
        t.writeTo(output)

    override val defaultValue: UsersProto
        get() = UsersProto.newBuilder().build()
}
