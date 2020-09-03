# music-api

Endpoints:


[X]/api/v1/bandas  
[X]/api/v1/banda/{banda_id}    
[X]/api/v1/banda/nova_banda  


[X]/api/v1/albums  
[X]/api/v1/album/{album_id}  
[X]/api/v1/album/novo_album  
[X]/api/v1/album/{album_id}/musicas  
[X]/api/v1/album/{album_id}/musica/{musica_id}  



```json
[
    {
        "id": 1,
        "nome": "Coldplay",
        "albums": [
            {
                "id": 1,
                "titulo": "A Head Full of Dreams",
                "ano": "2015",
                "musicas": [
                    {
                        "id": 2,
                        "nome": "Up&Up",
                        "duracao": 6:45,
                        "album": 1
                    },
                    {
                        "id": 1,
                        "nome": "Hymn for the Weekend",
                        "duracao": 4:19,
                        "album": 1
                    }
                ],
                "banda": 1
            }
        ]
    },
    {
        "id": 2,
        "nome": "Imagine Dragons",
        "albums": [
            {
                "id": 2,
                "titulo": "Origins",
                "ano": "2018",
                "musicas": [
                    {
                        "id": 3,
                        "nome": "Bad Lier",
                        "duracao": 4:21,
                        "album": 2
                    }
                ],
                "banda": 2
            }
        ]
    }
]
```
