package com.assignment.data.mapper.dtotoentity

import com.assginment.domain.entity.JokeEntity
import com.assignment.data.dto.JokeDto

fun JokeDto.map()= JokeEntity(
    joke=joke

)