package com.kaanyagan.fixtureapp.helper

import com.kaanyagan.fixtureapp.R
import com.kaanyagan.fixtureapp.network.networkhelper.ApiResult
import com.kaanyagan.fixtureapp.network.networkhelper.DataSourceException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

/**
 * extension function for Flow Class to emit loading state before the flow starts
 */
fun <T> Flow<ApiResult<T>>.onFlowStarts() =
    onStart {
        emit(ApiResult.Loading)
    }.catch {
        emit(ApiResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message)))
    }
