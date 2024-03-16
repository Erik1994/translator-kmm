//
//  IOSVoiceToTextViewModel.swift
//  iosApp
//
//  Created by Erik Antonyan on 17.03.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import Combine

@MainActor class IOSVoiceToTextViewModel: ObservableObject {
    private var parser: any VoiceToTextParser
    private let langugeCode: String
    
    private let viewModel: VoiceToTextViewmodel
    @Published var state = VoiceToTextState(powerRatios: [], spokenText: "", canRecord: false, recordError: nil, displayState: nil)
    private handle: DisposableHandle?
    
    init(parser: any VoiceToTextParser, langugeCode: String) {
        self.parser = parser
        self.langugeCode = langugeCode
        self.viewModel = VoiceToTextViewmodel(parser: parser, coroutineScope: nil)
    }
    
    func onEvent(event: VoiceToTextEvent) {
        viewModel.onEvent(event: event)
    }
    
    func startObserving() {
        handle = viewModel.state.subscribe { [weak self] state in
            if let state {
                self?.state = state
            }
        }
    }
    
    func dispose() {
        handle?.dispose()
        onEvent(event: VoiceToTextEvent.Reset())
    }
}
