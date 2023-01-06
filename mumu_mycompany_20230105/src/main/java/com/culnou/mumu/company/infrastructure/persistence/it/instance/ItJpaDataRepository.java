package com.culnou.mumu.company.infrastructure.persistence.it.instance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.it.category.ItCategoryId;
import com.culnou.mumu.company.domain.model.it.instance.It;
import com.culnou.mumu.company.domain.model.it.instance.ItId;
import com.culnou.mumu.company.domain.model.place.instance.PlaceId;

public interface ItJpaDataRepository extends JpaRepository<It, ItId> {

	public It findByItId(ItId itId);
	@Query(value = "select it from It it where it.companyId = :CompanyId")
	public List<It> findItsOfCompany(@Param("CompanyId") CompanyId companyId);
	@Query(value = "select it from It it where it.itCategoryId = :ItCategoryId")
	public List<It> findItsOfItCategory(@Param("ItCategoryId") ItCategoryId itCategoryId);
	@Query(value = "select it from It it where it.businessUnitId = :BusinessUnitId")
	public List<It> findItsOfBusinessUnit(@Param("BusinessUnitId") BusinessUnitId businessUnitId);
	@Query(value = "select it from It it where it.placeId = :PlaceId")
	public List<It> findItsOfPlace(@Param("PlaceId") PlaceId placeId);
}
