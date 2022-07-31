package dubbo.api.mock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockServiceMock implements MockService {

  @Override
  public String mock(String name) {
    log.info("====== {}.mock ======", this.getClass().getName());
    return "Mock OK---" + name;
  }
}
