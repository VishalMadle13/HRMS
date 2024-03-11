package com.github.vishalmadle13.HRMS.services

import com.github.vishalmadle13.HRMS.entites.User
import org.springframework.stereotype.Service
import java.util.*



@Service
class UserService {
    private val store: MutableList<User> = ArrayList<User>()

    init {
        store.add(
            User(
                UUID.randomUUID().toString(), "Prathiksha Kini",
                "gpkini2002@gmail.com"
            )
        )
        store.add(
            User(
                UUID.randomUUID().toString(), "Padmini Kini",
                "kinipadmini@gmail.com"
            )
        )
        store.add(
            User(
                UUID.randomUUID().toString(), "Mahalasa Kini",
                "kinimahalasa@gmail.com"
            )
        )
        store.add(
            User(
                UUID.randomUUID().toString(), "Gurudath Kini",
                "gurukini@gmail.com"
            )
        )
    }

    val users: List<Any>
        get() = store
}