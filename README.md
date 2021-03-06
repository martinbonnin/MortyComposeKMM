# MortyComposeKMM

Rick & Morty app to demonstrate use of GraphQL + Jetpack Compose (heavily based on https://github.com/Dimillian/MortyUI).
This is also a Kotlin Multiplatform project with GraphQL code in shared module (making use of [Apollo library's Kotlin Multiplatform support](https://www.apollographql.com/docs/android/essentials/get-started-multiplatform/)).
Right now app shows list of characters and episodes but plan is to update to show detail screens as well.


![BikeShare Screenshot](/art/characters_screenshot.png?raw=true)

The project also makes use of Jetpack Compose's [Paging library](https://developer.android.com/jetpack/androidx/releases/paging#paging_compose_version_100_2)
that allows setting up `LazyColumn` for example that's driven from `PagingSource` as shown below (that source in our case invokes Apollo GraphQL queries). 

```kotlin
class CharacterListsViewModel(private val repository: MortyRepository): ViewModel() {
    
    val characters: Flow<PagingData<GetCharactersQuery.Result>> = Pager(PagingConfig(pageSize = 20)) {
        CharactersDataSource(repository)
    }.flow

}

@Composable
fun CharactersListView() {
    val characterListsViewModel = getViewModel<CharacterListsViewModel>()
    val lazyCharacterList = characterListsViewModel.characters.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyCharacterList) { character ->
            character?.let {
                CharactersListRowView(character)
            }
        }
    }
}
```


