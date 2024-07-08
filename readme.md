
# URL SHORTENER

URL shortener: enter any link and receive another link, also check how many views it has received."

## Tech


<div style="display: flex; justify-content: space-around; max-width: 400px; margin: auto;align-items: center; flex-wrap: wrap;">

![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

</div>

## API Reference

#### Redirect to original URL

```http
  GET /{shortenID}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `shortenId` | `string` | **Required**. shortenID provided by the app. |

## Get clicks

```http
  GET /v/{shortenID}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `shortenUrl` | `string` | **Required**. shortenID provided by the app. |

``` Result-> Click on the link ```


## Get clicks

```http
  POST
```
```Query Param = ?originalUrl={your_url}```

``` Result -> shortenUrl ```

