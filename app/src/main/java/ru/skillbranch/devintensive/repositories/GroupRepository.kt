package ru.skillbranch.devintensive.repositories

import ru.skillbranch.devintensive.models.data.User
import ru.skillbranch.devintensive.utils.DataGenerator

class GroupRepository {
    fun loadUsers(): List<User> = DataGenerator.stabUsers

}