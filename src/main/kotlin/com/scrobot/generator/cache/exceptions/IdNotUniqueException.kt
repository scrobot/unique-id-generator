package com.scrobot.generator.cache.exceptions

class IdNotUniqueException(id: Long, nodeId: String?): Exception("Id '$id' is not unique on instance#${nodeId}")