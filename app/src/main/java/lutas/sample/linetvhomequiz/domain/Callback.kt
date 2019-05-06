package lutas.sample.linetvhomequiz.domain

class Callback<T>(
    onSuccess: (T?) -> Unit = {},
    onError: (Throwable) -> Unit = {},
    onDone: () -> Unit = {}
)