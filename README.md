# ID generation service

this service is intend for generation always unique **numerical** identification.  
Implementation inspired of Twitter snowflake https://developer.twitter.com/en/docs/basics/twitter-ids.

## What for?

If you have micro-service architecture sometimes for some entities you need to have unique ID. Usually, it's 
decides by UUID, but it has his own discomforts. So, another way is to use numerical ID in all system. 
Also, this service nice to be a stateless, because of in high load the persistent storage's become a bottleneck.
With stateless services there is no overhead on marshaling/unmarshalling, and it's easier to deploy.      

## Technological stack

- Kotlin
- Spring boot 2.2.6.RELEASE
- Webflux/Project Reactor
- Consul Service Discovery
- Springfox Swagger generator
- Caffeine caching library

## Quick start guide

You can pull docker image from public docker hub

`docker pull scrobot/unique-id-generator:latest`

and then just start it with needed parameters

```
docker run -d \
    --name id-generator \
    -e HOT_CACHE_MAX_SIZE=1000000     
    scrobot/unique-id-generator:latest
```

Notice: For working of this service you need to start **consul** before.

Or easier way, is to user `docker-compose.production.yaml` with command

`docker-compose -f docker-compose.production.yaml up -d` for production 
or if you need to start only consul(for development mode, if you fork this branch for example) 
`docker-compose up -d` for develop mode 