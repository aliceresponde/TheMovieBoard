package com.aliceresponde.themovieboard.data.remote

import java.io.IOException

class NoInternetException(message: String) : IOException(message)
class ApiException(message: String) : IOException(message)