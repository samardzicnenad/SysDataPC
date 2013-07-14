package datareporter

import org.springframework.dao.DataIntegrityViolationException

class NETutilController {

	static allowedMethods = []

	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[NETutilInstanceList: NETutil.list(params), NETutilInstanceTotal: NETutil.count()]
	}

	def show(Long id) {
		def NETutilInstance = NETutil.get(id)
		if (!NETutilInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'NETutil.label', default: 'NETutil'), id])
			redirect(action: "list")
			return
		}

		[NETutilInstance: NETutilInstance]
	}
}
