//
//  TextToSpeech.swift
//  iosApp
//
//  Created by Erik Antonyan on 13.03.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import AVFoundation

struct TextToSpeech {
    private let synthesizer = AVSpeechSynthesizer()
    
    func speak(text: String, language: String) {
        let uttrance = AVSpeechUtterance(string: text)
        uttrance.voice = AVSpeechSynthesisVoice(language: language)
        uttrance.volume = 1
        synthesizer.speak(uttrance)
    }
}
