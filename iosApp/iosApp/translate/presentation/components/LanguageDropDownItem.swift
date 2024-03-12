//
//  LanguageDropDownItem.swift
//  iosApp
//
//  Created by Erik Antonyan on 11.03.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import SwiftUI

struct LanguageDropDownItem: View {
    var language: UiLanguage
    var onClick: () -> Void
    
    var body: some View {
        Button(action: onClick) {
            HStack {
                if let image = UIImage(named: language.imageName.lowercased()) {
                    Image(uiImage: image)
                        .resizable()
                        .frame(width: 40, height: 40)
                        .padding(.trailing, 5)
                    Text(language.language.langName)
                        .foregroundColor(.textBlack)
                }
            }
        }
    }
}

struct LanguageDropDownItem_Previews: PreviewProvider {
    static var previews: some View {
        LanguageDropDownItem(
            language: UiLanguage(language: .english, imageName: "english"),
            onClick: {}
        )
    }
}
