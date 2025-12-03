package com.aallam.openai.client

import com.aallam.openai.api.chat.*
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.internal.TestFileSystem
import com.aallam.openai.client.internal.testFilePath
import kotlinx.io.buffered
import kotlinx.io.readByteArray
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.test.*

class TestChatVision : TestOpenAI() {

    private val sampleImageDataUrl by lazy { loadSampleImageDataUrl() }

    @Test
    fun textimage() = test {
        val request = chatCompletionRequest {
            model = ModelId("gpt-4o")
            messages {
                user {
                    content {
                        text("What’s in this image?")
                        image(sampleImageDataUrl)
                    }
                }
            }
            maxTokens = 300
        }
        val response = openAI.chatCompletion(request)
        val content = response.choices.first().message.content.orEmpty()
        assertNotNull(content)
    }

    @Test
    fun multiImage() = test {
        val request = chatCompletionRequest {
            model = ModelId("gpt-4o")
            messages {
                user {
                    content {
                        text("What are in these images? Is there any difference between them?")
                        image(sampleImageDataUrl)
                        image(sampleImageDataUrl)
                    }
                }
            }
            maxTokens = 300
        }
        val response = openAI.chatCompletion(request)
        val content = response.choices.first().message.content.orEmpty()
        assertNotNull(content)
    }

    @OptIn(ExperimentalEncodingApi::class)
    private fun loadSampleImageDataUrl(): String {
        val bytes = TestFileSystem.source(testFilePath("image/pets.png"))
            .buffered()
            .readByteArray()
        val base64 = Base64.Default.encode(bytes)
        return "data:image/png;base64,$base64"
    }
}
