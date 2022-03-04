package models

class ExcludeWithDependency(
    override val path: String,
    vararg val excludeDependency: ExcludeDependency
) : Dependency(path)