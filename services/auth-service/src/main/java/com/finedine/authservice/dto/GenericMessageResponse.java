package com.finedine.authservice.dto;

import lombok.Builder;

/** Record representing a generic message response.
 * This record is used to encapsulate a simple message
 * that can be returned in various API responses.
 */
@Builder
public record GenericMessageResponse(
        String message
) {
}
