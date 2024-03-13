//
//  LanguageDisplay.swift
//  iosApp
//
//  Created by Erik Antonyan on 12.03.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LanguageDisplay: View {
    var language: UiLanguage
    var body: some View {
        HStack {
            SmallLanguageIcon(uiLanguage: language)
                .padding(.trailing, 5)
            Text(language.language.langName)
                .foregroundColor(.lightBlue)
        }
    }
}

#Preview {
    LanguageDisplay(
        language: UiLanguage(
            language: .english,
            imageName:"english"
        )
    )
}
