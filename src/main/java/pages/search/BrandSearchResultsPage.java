package pages.search;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class BrandSearchResultsPage extends SearchPageBasic {

    public BrandSearchResultsPage() {
        checkAllIfExists();
    }
}
