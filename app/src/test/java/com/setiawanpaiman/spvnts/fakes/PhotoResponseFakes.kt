package com.setiawanpaiman.spvnts.fakes

import com.setiawanpaiman.spvnts.models.PhotoResponse

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
object PhotoResponseFakes {

    val PHOTO_RESPONSE_JSON = """
        [
          {
            "albumId": 1,
            "id": 1,
            "title": "title-1",
            "url": "http://url.com/photo1",
            "thumbnailUrl": "http://url.com/thumbnail1"
          },
          {
            "albumId": 1,
            "id": 2,
            "title": "title-2",
            "url": "http://url.com/photo2",
            "thumbnailUrl": "http://url.com/thumbnail2"
          }
        ]
    """.trimIndent()

    fun generatePhotosResponse(num: Int): List<PhotoResponse> {
        val list = mutableListOf<PhotoResponse>()
        for (i in 1..num) {
            list.add(generatePhotoResponse(i))
        }
        return list
    }

    fun generatePhotoResponse(id: Int): PhotoResponse =
        PhotoResponse(1, id, "title-$id","http://url.com/photo$id", "http://url.com/thumbnail$id")
}