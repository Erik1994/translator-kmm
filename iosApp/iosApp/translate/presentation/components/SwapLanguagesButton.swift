//
//  SwapLanguagesButton.swift
//  iosApp
//
//  Created by Erik Antonyan on 12.03.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SwapLanguagesButton: View {
    var onClick: () -> Void
    var body: some View {
        Button(action: onClick) {
            Image(uiImage: UIImage(named: "swap_languages")!)
                .padding()
                .background(Color.primaryColor)
                .clipShape(/*@START_MENU_TOKEN@*/Circle()/*@END_MENU_TOKEN@*/)
        }
    }
}

#Preview {
    SwapLanguagesButton(onClick: {})
}
