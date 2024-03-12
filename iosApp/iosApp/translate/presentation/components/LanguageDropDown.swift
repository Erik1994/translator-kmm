//
//  LanguageDropDown.swift
//  iosApp
//
//  Created by Erik Antonyan on 11.03.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LanguageDropDown: View {
    var language: UiLanguage
    var isOpen: Bool
    var selectLanguage: (UiLanguage) -> Void
    var body: some View {
        Menu {
            ForEach(UiLanguage.Companion().allLanguages, id:\.self.language.langCode) { language in
                LanguageDropDownItem(
                    language: language,
                    onClick: {
                        selectLanguage(language)
                    }
                )
            }
        } label: {
            HStack {
                SmallLanguageIcon(uiLanguage: language)
                Text(language.language.langName)
                    .foregroundColor(.lightBlue)
                Image(systemName: isOpen ? "chevron.up" : "chevron.down")
                    .foregroundColor(.lightBlue)
            }
        }
    }
}

#Preview {
    LanguageDropDown(
        language: UiLanguage(language: .english, imageName: "english"),
        isOpen: true,
        selectLanguage: {Language in}
    )
}
