//
//  TranslateScreen.swift
//  iosApp
//
//  Created by Erik Antonyan on 12.03.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TranslateScreen: View {
    private var historyDataSource: HistoryDataSource
    private var translateUseCase: TranslateUseCase
    private var getUiHistoryItemsUseCase: GetUiHistoryItemsUseCase
    @ObservedObject var viewmodel: IOSTranslateViewModel
    
    init(
        historyDataSource: HistoryDataSource,
        translateUseCase: TranslateUseCase,
        getUiHistoryItemsUseCase: GetUiHistoryItemsUseCase
    ) {
        self.historyDataSource = historyDataSource
        self.translateUseCase = translateUseCase
        self.getUiHistoryItemsUseCase = getUiHistoryItemsUseCase
        self.viewmodel = IOSTranslateViewModel(
            historyDataSource: historyDataSource,
            translateUseCase: translateUseCase,
            getUiHistoryItemsUseCase: getUiHistoryItemsUseCase
        )
    }
    
    var body: some View {
        ZStack {
            List {
                HStack(alignment: .center) {
                    LanguageDropDown(
                        language: viewmodel.state.fromLanguage,
                        isOpen: viewmodel.state.isChoosingFromLanguage,
                        selectLanguage: { language in
                            viewmodel.onEvent(event: TranslateEvent.ChooseFromLanguage(language: language))
                        }
                    )
                    Spacer()
                    SwapLanguagesButton(onClick: {
                        viewmodel.onEvent(event: TranslateEvent.SwapLanguage())
                    })
                    Spacer()
                    LanguageDropDown(
                        language: viewmodel.state.toLanguage,
                        isOpen: viewmodel.state.isChoosingToLanguage,
                        selectLanguage: { language in
                            viewmodel.onEvent(event: TranslateEvent.ChooseToLanguage(language: language))
                        }
                    )
                }
                .listRowSeparator(.hidden)
                .listRowBackground(Color.background)
                
                TranslateTextField(
                    fromText: Binding(get: { viewmodel.state.fromText },
                                      set: { value in
                                          viewmodel.onEvent(event: TranslateEvent.ChangeTranslationText(text: value))
                                      }
                                     ),
                    toText: viewmodel.state.toText,
                    isTranslating: viewmodel.state.isTranslating,
                    fromLanguage: viewmodel.state.fromLanguage,
                    toLanguage: viewmodel.state.toLanguage,
                    onTranslateEvent: { viewmodel.onEvent(event: $0) }//$0 is equivalent "it" in Kotlin inside some lambda
                )
                .listRowSeparator(.hidden)
                .listRowBackground(Color.background)
                
                if !viewmodel.state.histories.isEmpty {
                    Text("History")
                        .font(.title.bold())
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .listRowSeparator(.hidden)
                        .listRowBackground(Color.background)
                }
                
                ForEach(viewmodel.state.histories, id: \.self.id) { item in
                    TranslateHistoryItem(
                        historyItem: item, 
                        onClick: {
                            viewmodel.onEvent(event: TranslateEvent.SelectHistoryItem(item: item))
                        }
                    )
                    .listRowSeparator(.hidden)
                    .listRowBackground(Color.background)
                }
            }
            .listStyle(.plain)
            .buttonStyle(.plain)
            
            VStack {
                Spacer()
                NavigationLink(destination: Text("Voice-to-text screen")) {
                    ZStack {
                        Circle()
                            .foregroundColor(.primaryColor)
                            .padding()
                        Image(uiImage: UIImage(named: "mic")!)
                            .foregroundColor(.onPrimary)
                    }
                    .frame(width: /*@START_MENU_TOKEN@*/100/*@END_MENU_TOKEN@*/, height: 100)
                }
            }
        }
        .onAppear {
            viewmodel.startObserve()
        }
        .onDisappear {
            viewmodel.dispose()
        }
    }
}

//#Preview {
//    TranslateScreen()
//}
