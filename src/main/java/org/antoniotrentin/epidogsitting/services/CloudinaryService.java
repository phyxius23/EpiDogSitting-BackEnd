package org.antoniotrentin.epidogsitting.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {

	Cloudinary cloudinary;
	//  codice per rendere private le chiavi di cloudinary (non funziona => forse perchè sto in un service?)
	//	private String cloudName;
	//	private static String apiKey;
	//	private static String apiSecret;
	//
	//	@Value("${spring.application.cloudinary.cloud_name}")
	//	public void setCloudName(String cn) {
	//		cloudName = cn;
	//	}
	//
	//	@Value("${spring.application.cloudinary.api_key}")
	//	public void setApiKey(String ak) {
	//		apiKey = ak;
	//	}
	//
	//	@Value("${spring.application.cloudinary.api_secret}")
	//	public void setApiSecret(String as) {
	//		apiSecret = as;
	//	}

	private Map<String, String> valuesMap = new HashMap<>();

	public CloudinaryService() {
		valuesMap.put("cloud_name", "dqnclur2i");
		valuesMap.put("api_key", "651354624433853");
		valuesMap.put("api_secret", "KnO-3DB_dIoKFsKobZdEF6Pa6Yc");
		cloudinary = new Cloudinary(valuesMap);
	}

	public Map upload(MultipartFile multipartFile) throws IOException {
		File file = convert(multipartFile);
		Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		file.delete();
		return result;
	}

	public Map delete(String id) throws IOException {
		Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
		return result;
	}

	private File convert(MultipartFile multipartFile) throws IOException {
		File file = new File(multipartFile.getOriginalFilename());
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(multipartFile.getBytes());
		fo.close();
		return file;
	}
}
