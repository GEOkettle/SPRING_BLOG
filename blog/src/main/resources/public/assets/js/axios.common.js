axios.defaults.timeout = 600000;
axios.defaults.baseURL = window.location.origin;
axios.defaults.headers['Cache-Control'] = "no-cache,no-store,must-revalidate,max-age=-1,private";
/*axios.defaults.paramsSerializer = function(params) {
	if(typeof params == 'object' && _.size(params) > 0) {
		console.log('params : '+JSON.stringify(params));

		_.forEach(params, function(value, key) {
			params[key] = encodeURIComponent(value);
		});
	}

    return params;
};*/
axios.defaults.validateStatus = function (status) {
	/*
	 * 에러는 일단 모두 통과 시킨다. (화면에서 무한로딩할 수 없기때문에)
	 * 단 에러상태에 따른 로그를 남기는 처리를 하여야 함.
	 */
	/* 400 대 요청 */
	/*
	400(잘못된 요청): 서버가 요청의 구문을 인식하지 못했다.
	401(권한 없음): 이 요청은 인증이 필요하다. 서버는 로그인이 필요한 페이지에 대해 이 요청을 제공할 수 있다. 상태 코드 이름이 권한 없음(Unauthorized)으로 되어 있지만 실제 뜻은 인증 안됨(Unauthenticated)에 더 가깝다.[2]
	402(결제 필요): 이 요청은 결제가 필요합니다.
	403(Forbidden, 금지됨): 서버가 요청을 거부하고 있다. 예를 들자면, 사용자가 리소스에 대한 필요 권한을 갖고 있지 않다. (401은 인증 실패, 403은 인가 실패라고 볼 수 있음)
	404(Not Found, 찾을 수 없음): 서버가 요청한 페이지(Resource)를 찾을 수 없다. 예를 들어 서버에 존재하지 않는 페이지에 대한 요청이 있을 경우 서버는 이 코드를 제공한다.
	405(허용되지 않는 방법): 요청에 지정된 방법을 사용할 수 없다. 예를 들어 POST 방식으로 요청을 받는 서버에 GET 요청을 보내는 경우, 또는 읽기 전용 리소스에 PUT 요청을 보내는 경우에 이 코드를 제공한다.
	406(허용되지 않음): 요청한 페이지가 요청한 콘텐츠 특성으로 응답할 수 없다.
	407(프록시 인증 필요): 이 상태 코드는 401(권한 없음)과 비슷하지만 요청자가 프록시를 사용하여 인증해야 한다. 서버가 이 응답을 표시하면 요청자가 사용할 프록시를 가리키는 것이기도 한다.
	408(요청 시간초과): 서버의 요청 대기가 시간을 초과하였다.
	409(충돌): 서버가 요청을 수행하는 중에 충돌이 발생했다. 서버는 응답할 때 충돌에 대한 정보를 포함해야 한다. 서버는 PUT 요청과 충돌하는 PUT 요청에 대한 응답으로 이 코드를 요청 간 차이점 목록과 함께 표시해야 한다.
	410(사라짐): 서버는 요청한 리소스가 영구적으로 삭제되었을 때 이 응답을 표시한다. 404(찾을 수 없음) 코드와 비슷하며 이전에 있었지만 더 이상 존재하지 않는 리소스에 대해 404 대신 사용하기도 한다. 리소스가 영구적으로 이동된 경우 301을 사용하여 리소스의 새 위치를 지정해야 한다.
	411(길이 필요): 서버는 유효한 콘텐츠 길이 헤더 입력란 없이는 요청을 수락하지 않는다.
	412(사전조건 실패): 서버가 요청자가 요청 시 부과한 사전조건을 만족하지 않는다.
	413(요청 속성이 너무 큼): 요청이 너무 커서 서버가 처리할 수 없다.
	414(요청 URI가 너무 긺): 요청 URI(일반적으로 URL)가 너무 길어 서버가 처리할 수 없다.
	415(지원되지 않는 미디어 유형): 요청이 요청한 페이지에서 지원하지 않는 형식으로 되어 있다.
	416(처리할 수 없는 요청범위): 요청이 페이지에서 처리할 수 없는 범위에 해당되는 경우 서버는 이 상태 코드를 표시한다.
	417(예상 실패): 서버는 Expect 요청 헤더 입력란의 요구사항을 만족할 수 없다.
	418(I'm a teapot, RFC 2324)
	420(Enhance Your Calm, 트위터)
	422(처리할 수 없는 엔티티, WebDAV; RFC 4918)
	423(잠김,WebDAV; RFC 4918): 접근하려는 리소스가 잠겨 있다.
	424(실패된 의존성, WebDAV; RFC 4918)
	424(메쏘드 실패, WebDAV)
	425(정렬되지 않은 컬렉션, 인터넷 초안)
	426(업그레이드 필요, RFC 2817): 클라이언트는 업그레이드 헤더 필드에 주어진 프로토콜로 요청을 보내야 한다.
	428(전제조건 필요, RFC 6585)
	429(너무 많은 요청, RFC 6585): 사용자가 일정 시간 동안 너무 많은 요청을 보냈다.
	431(요청 헤더 필드가 너무 큼, RFC 6585)
	444(응답 없음, Nginx)
	449(다시 시도, 마이크로소프트)
	450(윈도 자녀 보호에 의해 차단됨, 마이크로소프트)
	451(법적인 이유로 이용 불가, 인터넷 초안)
	451(리다이렉션, 마이크로소프트)
	494(요청 헤더가 너무 큼, Nginx)
	495(Cert 오류, Nginx)
	496(Cert 없음, Nginx)
	497(HTTP to HTTPS, Nginx)
	499(클라이언트가 요청을 닫음, Nginx)
	*/

	/* 500대 요청 */
	/*
	500(내부 서버 오류): 서버에 오류가 발생하여 요청을 수행할 수 없다.
	501(구현되지 않음): 서버에 요청을 수행할 수 있는 기능이 없다. 예를 들어 서버가 요청 메소드를 인식하지 못할 때 이 코드를 표시한다.
	502(Bad Gateway, 불량 게이트웨이): 서버가 게이트웨이나 프록시 역할을 하고 있거나 또는 업스트림 서버에서 잘못된 응답을 받았다.
	503(서비스를 사용할 수 없음): 서버가 오버로드되었거나 유지관리를 위해 다운되었기 때문에 현재 서버를 사용할 수 없다. 이는 대개 일시적인 상태이다.
	504(게이트웨이 시간초과): 서버가 게이트웨이나 프록시 역할을 하고 있거나 또는 업스트림 서버에서 제때 요청을 받지 못했다.
	505(HTTP 버전이 지원되지 않음): 서버가 요청에 사용된 HTTP 프로토콜 버전을 지원하지 않는다.
	506(Variant Also Negotiates, RFC 2295)
	507(용량 부족, WebDAV; RFC 4918)
	508(루프 감지됨, WebDAV; RFC 5842)
	509(대역폭 제한 초과, Apache bw/limited extension)
	510(확장되지 않음, RFC 2774)
	511(네트워크 인증 필요, RFC 6585)
	520(Unknown Error, 알 수 없음)
	598(네트워크 읽기 시간초과 오류, 알 수 없음)
	599(네트워크 연결 시간초과 오류, 알 수 없음)
	*/
    return true;
};

/**********************************************************************************************************
{
	// `url` is the server URL that will be used for the request
	url: '/user',

	// `method` is the request method to be used when making the request
	method: 'get', // default

	// `baseURL` will be prepended to `url` unless `url` is absolute.
	// It can be convenient to set `baseURL` for an instance of axios to pass relative URLs
	// to methods of that instance.
	baseURL: 'https://some-domain.com/api/',

	// `transformRequest` allows changes to the request data before it is sent to the server
	// This is only applicable for request methods 'PUT', 'POST', and 'PATCH'
	// The last function in the array must return a string or an instance of Buffer, ArrayBuffer,
	// FormData or Stream
	// You may modify the headers object.
	transformRequest: [function (data, headers) {
	  // Do whatever you want to transform the data

	  return data;
	}],

	// `transformResponse` allows changes to the response data to be made before
	// it is passed to then/catch
	transformResponse: [function (data) {
	  // Do whatever you want to transform the data

	  return data;
	}],

	// `headers` are custom headers to be sent
	headers: {'X-Requested-With': 'XMLHttpRequest'},

	// `params` are the URL parameters to be sent with the request
	// Must be a plain object or a URLSearchParams object
	params: {
	  ID: 12345
	},

	// `paramsSerializer` is an optional function in charge of serializing `params`
	// (e.g. https://www.npmjs.com/package/qs, http://api.jquery.com/jquery.param/)
	paramsSerializer: function (params) {
	  return Qs.stringify(params, {arrayFormat: 'brackets'})
	},

	// `data` is the data to be sent as the request body
	// Only applicable for request methods 'PUT', 'POST', and 'PATCH'
	// When no `transformRequest` is set, must be of one of the following types:
	// - string, plain object, ArrayBuffer, ArrayBufferView, URLSearchParams
	// - Browser only: FormData, File, Blob
	// - Node only: Stream, Buffer
	data: {
	  firstName: 'Fred'
	},

	// `timeout` specifies the number of milliseconds before the request times out.
	// If the request takes longer than `timeout`, the request will be aborted.
	timeout: 1000, // default is `0` (no timeout)

	// `withCredentials` indicates whether or not cross-site Access-Control requests
	// should be made using credentials
	withCredentials: false, // default

	// `adapter` allows custom handling of requests which makes testing easier.
	// Return a promise and supply a valid response (see lib/adapters/README.md).
	adapter: function (config) {

	},

	// `auth` indicates that HTTP Basic auth should be used, and supplies credentials.
	// This will set an `Authorization` header, overwriting any existing
	// `Authorization` custom headers you have set using `headers`.
	auth: {
	  username: 'janedoe',
	  password: 's00pers3cret'
	},

	// `responseType` indicates the type of data that the server will respond with
	// options are 'arraybuffer', 'blob', 'document', 'json', 'text', 'stream'
	responseType: 'json', // default

	// `responseEncoding` indicates encoding to use for decoding responses
	// Note: Ignored for `responseType` of 'stream' or client-side requests
	responseEncoding: 'utf8', // default

	// `xsrfCookieName` is the name of the cookie to use as a value for xsrf token
	xsrfCookieName: 'XSRF-TOKEN', // default

	// `xsrfHeaderName` is the name of the http header that carries the xsrf token value
	xsrfHeaderName: 'X-XSRF-TOKEN', // default

	// `onUploadProgress` allows handling of progress events for uploads
	onUploadProgress: function (progressEvent) {
	  // Do whatever you want with the native progress event
	},

	// `onDownloadProgress` allows handling of progress events for downloads
	onDownloadProgress: function (progressEvent) {
	  // Do whatever you want with the native progress event
	},

	// `maxContentLength` defines the max size of the http response content in bytes allowed
	maxContentLength: 2000,

	// `validateStatus` defines whether to resolve or reject the promise for a given
	// HTTP response status code. If `validateStatus` returns `true` (or is set to `null`
	// or `undefined`), the promise will be resolved; otherwise, the promise will be
	// rejected.
	validateStatus: function (status) {
	  return status >= 200 && status < 300; // default
	},

	// `maxRedirects` defines the maximum number of redirects to follow in node.js.
	// If set to 0, no redirects will be followed.
	maxRedirects: 5, // default

	// `socketPath` defines a UNIX Socket to be used in node.js.
	// e.g. '/var/run/docker.sock' to send requests to the docker daemon.
	// Only either `socketPath` or `proxy` can be specified.
	// If both are specified, `socketPath` is used.
	socketPath: null, // default

	// `httpAgent` and `httpsAgent` define a custom agent to be used when performing http
	// and https requests, respectively, in node.js. This allows options to be added like
	// `keepAlive` that are not enabled by default.
	httpAgent: new http.Agent({ keepAlive: true }),
	httpsAgent: new https.Agent({ keepAlive: true }),

	// 'proxy' defines the hostname and port of the proxy server.
	// You can also define your proxy using the conventional `http_proxy` and
	// `https_proxy` environment variables. If you are using environment variables
	// for your proxy configuration, you can also define a `no_proxy` environment
	// variable as a comma-separated list of domains that should not be proxied.
	// Use `false` to disable proxies, ignoring environment variables.
	// `auth` indicates that HTTP Basic auth should be used to connect to the proxy, and
	// supplies credentials.
	// This will set an `Proxy-Authorization` header, overwriting any existing
	// `Proxy-Authorization` custom headers you have set using `headers`.
	proxy: {
	  host: '127.0.0.1',
	  port: 9000,
	  auth: {
	    username: 'mikeymike',
	    password: 'rapunz3l'
	  }
	},

	// `cancelToken` specifies a cancel token that can be used to cancel the request
	// (see Cancellation section below for details)
	cancelToken: new CancelToken(function (cancel) {
	})
}
*****************************************************************************************************************/

axios.interceptors.request.use(function (config) {
	try{
		var defaultInfo = '';

		if(typeof globalDefaultInfo == 'object'){
			if(self == top) defaultInfo = globalDefaultInfo;
			else defaultInfo = parent.globalDefaultInfo;

			if(config.url.indexOf('bou-univ') > -1){
				config.url = (config.url).replace('bou-univ',defaultInfo.CAMPUS_URL_ID);
			}
		}

		if(config.loading){
			document.body.classList.add('loading-indicator');
		}

		const token = window.localStorage.token;
	  	if (token) {
	     	config.headers.Authorization = 'token ' + token; //`token ${token}`
	  	}
	  	;
		return config;
	}catch(err){
		console.log('axios.interceptors.request Error : '+err.message);
		return false;
	}
}, function (error) {
	return Promise.reject(error);
});

axios.interceptors.response.use(function (response) {
	try{
		var status = response.data.status;

		if(response.config.loading){
			document.body.classList.remove('loading-indicator');
		}

		//에러처리
		if(typeof status == 'string') {
			var date = new Date(response.data.timestamp);
			var error = response.data.error;
			var message = response.data.message;
			var returnVal = '['+status+'] '+date+' : ('+error+') ';

			//특정 메시지 들은 캐치하여 내부정보가 보이지 않는 error로 치환하여 반환한다.
			if(message.indexOf('PRIMARY KEY') > -1){
				message = 'E0001';
			}
			else if(message.indexOf('.jdbc.') > -1){
				message = 'E0002';
			}
			else if(message.indexOf('SQLServerException') > -1){
				message = 'E0003';
			} else if (error.indexOf('Access Denied') > -1 || message.indexOf('Access Denied') > -1) {
				alert(message);
				if(self == top){
					window.location.href = '/';
				}else{
					parent.window.location.href = '/';
				}
			} else{
				message = 'E9999';
			}

			returnVal += message;
			notifySubmit('danger', null, '['+message+'] 오류가 발생하였습니다. 관리자에게 문의바랍니다.', null);
			return Promise.reject(returnVal);
		}

	  	return response;
	}catch(err){
		console.log('axios.interceptors.response Error : '+err.message);
		return false;
	}
}, function (error) {
	return Promise.reject(error);
});