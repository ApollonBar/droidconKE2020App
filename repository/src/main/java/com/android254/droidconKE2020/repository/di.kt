package com.android254.droidconKE2020.repository

import com.android254.droidconKE2020.repository.feed.FeedRepository
import com.android254.droidconKE2020.repository.feed.FeedRepositoryImpl
import com.android254.droidconKE2020.repository.feedback.EventFeedbackRepository
import com.android254.droidconKE2020.repository.feedback.EventFeedbackRepositoryImpl
import org.koin.dsl.module

val repoModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }
    single<FeedRepository> { FeedRepositoryImpl(get()) }
    single<EventFeedbackRepository> { EventFeedbackRepositoryImpl(get()) }
}