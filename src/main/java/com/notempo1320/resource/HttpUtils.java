package com.notempo1320.resource;
import com.google.common.base.Optional;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.UUID;


final class HttpUtils {

	public static URI getCreatedResourceURI (UriInfo info,
        URI resourcePath, Long resourceId) {
		URI uri = info.getAbsolutePathBuilder()
				.uri(resourcePath).path(info.getPath())
				.path(resourceId.toString())
				.build();

		return uri;
	}

}
