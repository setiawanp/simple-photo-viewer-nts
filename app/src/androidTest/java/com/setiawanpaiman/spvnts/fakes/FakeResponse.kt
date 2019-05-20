package com.setiawanpaiman.spvnts.fakes

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
object FakeResponse {

    val EMPTY_PHOTO_RESPONSE_JSON = "[]"

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
          },
          {
            "albumId": 1,
            "id": 3,
            "title": "title-3",
            "url": "http://url.com/photo3",
            "thumbnailUrl": "http://url.com/thumbnail3"
          },
          {
            "albumId": 1,
            "id": 4,
            "title": "title-4",
            "url": "http://url.com/photo4",
            "thumbnailUrl": "http://url.com/thumbnail4"
          },
          {
            "albumId": 1,
            "id": 5,
            "title": "title-5",
            "url": "http://url.com/photo5",
            "thumbnailUrl": "http://url.com/thumbnail5"
          }
        ]
    """.trimIndent()
}