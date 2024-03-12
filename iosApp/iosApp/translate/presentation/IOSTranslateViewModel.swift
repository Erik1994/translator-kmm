//
//  IOSTranslateViewModel.swift
//  iosApp
//
//  Created by Erik Antonyan on 12.03.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension TranslateScreen {
    @MainActor class IOSTranslateViewModel: ObservableObject {
        private var historyDataSource: HistoryDataSource
        private var translateUseCase: TranslateUseCase
        private var getUiHistoryItemsUseCase: GetUiHistoryItemsUseCase
        
        private let viewModel: TranslateViewModel
        
        @Published var state: TranslateState = TranslateState(
            fromText: "",
            toText: nil,
            isTranslating: false,
            fromLanguage: UiLanguage(language: .english, imageName: "english"),
            toLanguage: UiLanguage(language: .russian, imageName: "russian"),
            isChoosingFromLanguage: false,
            isChoosingToLanguage: false,
            error: nil,
            histories: []
        )
        private var handle: DisposableHandle?
        
        init(historyDataSource: HistoryDataSource, translateUseCase: TranslateUseCase, getUiHistoryItemsUseCase: GetUiHistoryItemsUseCase) {
            self.historyDataSource = historyDataSource
            self.translateUseCase = translateUseCase
            self.getUiHistoryItemsUseCase = getUiHistoryItemsUseCase
            viewModel = TranslateViewModel(
                translateUseCase: translateUseCase,
                getUiHistoryItemsUseCase: getUiHistoryItemsUseCase, 
                coroutineScope: nil
            )
        }
        
        func startObserve() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }
        
        func onEvent(event: TranslateEvent) {
            viewModel.onEvent(event: event)
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
