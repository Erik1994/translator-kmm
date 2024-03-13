//
//  TranslateHistoryItem.swift
//  iosApp
//
//  Created by Erik Antonyan on 13.03.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TranslateHistoryItem: View {
    let historyItem: UiHistoryItem
    let onClick: () -> Void
    
    var body: some View {
        Button(action: onClick) {
            VStack(alignment: .leading) {
                HStack {
                    SmallLanguageIcon(uiLanguage: historyItem.fromLanguage)
                        .padding(.trailing)
                    Text(historyItem.fromText)
                        .foregroundColor(.lightBlue)
                        .font(.body)
                }
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.bottom)
    
                HStack {
                    SmallLanguageIcon(uiLanguage: historyItem.toLanguage)
                        .padding(.trailing)
                    Text(historyItem.toText)
                        .foregroundColor(.onSurface)
                        .font(.body.weight(.semibold))
                }
                .frame(maxWidth: .infinity, alignment: .leading)
            }
            .frame(maxWidth: .infinity)
            .padding()
            .gradientSurface()
            .cornerRadius(15)
            .shadow(radius: 4)
        }
    }
}

#Preview {
    TranslateHistoryItem(
        historyItem: UiHistoryItem(
            id: 1,
            fromText: "I am a programmer",
            toText: "Я програмист",
            fromLanguage: UiLanguage(language: .english, imageName: "english"), toLanguage: UiLanguage(language: .russian, imageName: "russian")
        ),
        onClick: {}
    )
}
