//
//  SmallLanguageIcon.swift
//  iosApp
//
//  Created by Erik Antonyan on 12.03.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SmallLanguageIcon: View {
    var uiLanguage: UiLanguage
    var body: some View {
        Image(uiImage: UIImage(named: uiLanguage.imageName.lowercased())!)
            .resizable()
            .frame(width: 30, height: 30)
    }
}

#Preview {
    SmallLanguageIcon(
        uiLanguage: UiLanguage(
            language: .english,
            imageName: "english"
        )
    )
}
