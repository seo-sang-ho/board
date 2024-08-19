package article.projectNum1.write.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import article.projectNum1.write.entity.Write;
import article.projectNum1.write.entity.WriteUpdateForm;
import article.projectNum1.write.repository.WriteRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class WriteService {

	private final WriteRepository writeRepository;

	public void save(Write write) {
		writeRepository.save(write);
	}

	public Write findById(Long id) {
		return writeRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("작성 글이 없습니다."));
	}

	public List<Write> findAll(){
		return writeRepository.findAll();
	}

	public void update(Long id, WriteUpdateForm writeUpdateDto){
		Write findWrite = findById(id);
		findWrite.setTitle(writeUpdateDto.getTitle());
		findWrite.setContent(writeUpdateDto.getContent());
		findWrite.setModify_at(LocalDateTime.now());
	}

	public void delete(Write write) {
		writeRepository.delete(write);
	}
}
